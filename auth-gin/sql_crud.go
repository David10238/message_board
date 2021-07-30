package main

import (
	"math/rand"
	"time"
)

func DB_CreateUser(username string, password string) *User {
	user := User{
		Username: username,
		Password: password,
		Token:    randomToken(),
	}

	if DB_NameExists(username) {
		return nil
	}

	db.Create(&user)
	return &user
}

func DB_NameExists(username string) bool {
	var users []User
	return db.Where("username = ?", username).Find(&users).RowsAffected != 0
}

func DB_FindUserByPassword(username string, password string) (bool, User) {
	//todo fix record not found
	var user User
	foundUser := db.Where("\"username\" = ? AND \"password\" = ?", username, password).First(&user).RowsAffected != 0
	return foundUser, user
}

func DB_FindUserByToken(username string, token string) (bool, User) {
	//todo fix record not found
	var user User
	foundUser := db.Where("\"username\" = ? AND \"token\" = ?", username, token).First(&user).RowsAffected != 0
	return foundUser, user
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
