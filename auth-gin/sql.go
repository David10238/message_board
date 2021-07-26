package main

import (
	"fmt"

	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

type User struct {
	ID       uint `gorm:"primary_key"`
	Username string
	Password string
	Token    string
}

func setupDB() *gorm.DB {
	dsn := "postgresql://postgres:password@localhost:5432/message_auth_db"
	db, err := gorm.Open(postgres.Open(dsn), &gorm.Config{})
	if err != nil {
		fmt.Println(err)
		panic("issue connecting to table")
	}

	db.AutoMigrate(&User{})
	//db.Create(&User{ID: 1, username: "user", password: "pass", token: "tok"})
	return db
}

var db = setupDB()
