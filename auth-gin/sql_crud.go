package main

import (
	"math/rand"
	"time"
)

func DB_CreateUser(username string, password string) bool {
	user := User{
		Username: username,
		Password: password,
		Token:    randomToken(),
	}

	if DB_NameExists(username) {
		return false
	}

	db.Create(&user)
	return true
}

func DB_NameExists(username string) bool {
	var users []User
	return db.Where("username = ?", username).Find(&users).RowsAffected != 0
}

func randomToken() string {
	const letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
	s1 := rand.NewSource(time.Now().UnixNano())
	r1 := rand.New(s1)

	b := make([]byte, 64)

	for i := range b {
		b[i] = letters[r1.Intn(len(letters))]
	}

	return string(b)
}
