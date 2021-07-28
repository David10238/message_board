package main

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"log"
	"net/http"
	"strings"
)

type LangIndex struct {
	Tag  string `json:"languageTag"`
	Name string `json:"languageName"`
}

const (
	PORT             = ":5004"
	INVALID_TAG      = "INVALID_TAG"
	FILE_NAME_LENGTH = 8
	JSON_TAG_LENGTH  = 5
	TAG_SIZE         = 3
	JSON_EXTENSION   = ".json"
	ALL_LANGS_ROUTE  = "index"
)

func load(tag string) string {
	dat, err := ioutil.ReadFile(fmt.Sprintf("json/%s.json", tag))
	if err != nil {
		panic(err)
	}
	return strings.TrimSpace(string(dat))
}

func makeAllLangsStr(langs *map[string]string) string {
	list := make([]LangIndex, 0)
	for tag, lang := range *langs {
		dat := make(map[string]string)
		if err := json.Unmarshal([]byte(lang), &dat); err != nil {
			panic(err)
		}
		list = append(list, LangIndex{Tag: tag, Name: dat["languageName"]})
	}
	str, err := json.Marshal(list)
	if err != nil {
		panic(err)
	}
	fmt.Println(list)
	return string(str)
}

func main() {
	files, err := ioutil.ReadDir("json")
	if err != nil {
		panic(err)
	}
	langs := make(map[string]string)
	for _, file := range files {
		f := file.Name()
		lang_tag := f[:TAG_SIZE]
		if f[len(f)-JSON_TAG_LENGTH:] == JSON_EXTENSION && len(f) == FILE_NAME_LENGTH {
			langs[lang_tag] = load(lang_tag)
		}
	}

	ALL_LANGS_STR := makeAllLangsStr(&langs)

	fmt.Printf("luanching at http://127.0.0.1%s\n", PORT)
	http.HandleFunc("/", func(w http.ResponseWriter, r *http.Request) {
		tag := r.RequestURI[1:]

		if tag == ALL_LANGS_ROUTE {
			w.Header().Set("Content-Type", "application/json")
			fmt.Fprint(w, ALL_LANGS_STR)
			return
		}

		if len(tag) != 3 {
			http.Error(w, INVALID_TAG, http.StatusNotFound)
			return
		}

		lang := langs[tag]
		if len(lang) == 0 {
			http.Error(w, INVALID_TAG, http.StatusNotFound)
			return
		}

		fmt.Fprint(w, lang)
	})
	log.Fatal(http.ListenAndServe(PORT, nil))
}
