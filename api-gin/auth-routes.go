package main

import "github.com/gin-gonic/gin"

func AuthRoutes(server *gin.Engine) {
	server.DELETE("/auth/delete")
	server.PATCH("/auth/changePassword")
	server.PATCH("/auth/refreshToken")
	server.GET("/auth/doesNameExist")
	server.GET("/auth/token")
	server.POST("/auth/create")
}

func deleteUser() {

}

func changePassword() {

}

func refreshPassword() {

}

func doesNameExist() {

}

func getToken() {

}

func createNewUser() {

}
