package main

import (
	"github.com/gin-gonic/gin"
)

func Routes(server *gin.Engine) {
	server.POST("/addUser", addUser)
	server.DELETE("/deleteUser", deleteUser)
	server.PATCH("/changePassword", changePassword)
	server.PATCH("/refreshToken", refreshToken)
	server.GET("/doesNameExist", doesNameExist)
	server.GET("/getToken", getToken)
	server.GET("/byPassword", byPassword)
	server.GET("/byToken", byToken)
}

func changePassword(ctx *gin.Context) {
}

func deleteUser(ctx *gin.Context) {
}

func addUser(ctx *gin.Context) {
}

func byPassword(ctx *gin.Context) {
}

func byToken(ctx *gin.Context) {
}

func doesNameExist(ctx *gin.Context) {
}

func getToken(ctx *gin.Context) {
}

func refreshToken(ctx *gin.Context) {
}
