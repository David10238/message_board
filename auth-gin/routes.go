package main

import (
	"fmt"
	"net/http"

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

func addUser(ctx *gin.Context) {
	username, password, err := GetUsernameAndPassword(ctx)
	fmt.Printf("%s, %s\n", username, password)
	if err {
		ctx.String(http.StatusNotFound, MISSING_PARAMETER)
		return
	}

	createdNew := DB_CreateUser(username, password)
	ctx.JSON(http.StatusAccepted, createdNew)
}

func deleteUser(ctx *gin.Context) {
	username, password, err := GetUsernameAndPassword(ctx)
	fmt.Printf("%s, %s\n", username, password)
	if err {
		ctx.String(http.StatusNotFound, MISSING_PARAMETER)
		return
	}

	// todo implement
}

func changePassword(ctx *gin.Context) {
	username, password, errUserAndPassword := GetUsernameAndPassword(ctx)
	newPassword, errNewPassword := GetNewPassword(ctx)
	fmt.Printf("%s, %s, %s\n", username, password, newPassword)
	if errUserAndPassword || errNewPassword {
		ctx.String(http.StatusNotFound, MISSING_PARAMETER)
		return
	}

	found, user := DB_FindUserByPassword(username, password)

	if !found {
		ctx.String(http.StatusUnauthorized, INVALID_CREDENTIALS)
		return
	}

	user.Password = newPassword
	db.Save(&user)
}

func refreshToken(ctx *gin.Context) {
	username, token, err := GetUsernameAndToken(ctx)
	fmt.Printf("%s, %s\n", username, token)
	if err {
		ctx.String(http.StatusNotFound, MISSING_PARAMETER)
		return
	}

	found, user := DB_FindUserByToken(username, token)

	if !found {
		ctx.String(http.StatusUnauthorized, INVALID_CREDENTIALS)
		return
	}

	newToken := randomToken()
	user.Token = newToken
	db.Save(&user)

	//todo add error case for if changing token fails
	ctx.JSON(http.StatusAccepted, newToken)
}

func doesNameExist(ctx *gin.Context) {
	username, err := GetUsername(ctx)
	fmt.Printf("%s\n", username)
	if err {
		ctx.String(http.StatusNotFound, MISSING_PARAMETER)
		return
	}

	ctx.JSON(http.StatusAccepted, DB_NameExists(username))
}

func getToken(ctx *gin.Context) {
	username, password, err := GetUsernameAndPassword(ctx)
	fmt.Printf("%s, %s\n", username, password)
	if err {
		ctx.String(http.StatusNotFound, MISSING_PARAMETER)
		return
	}

	found, user := DB_FindUserByPassword(username, password)

	if !found {
		ctx.String(http.StatusUnauthorized, INVALID_CREDENTIALS)
		return
	}

	ctx.JSON(http.StatusAccepted, user.Token)
}

func byPassword(ctx *gin.Context) {
	username, password, err := GetUsernameAndPassword(ctx)
	fmt.Printf("%s, %s\n", username, password)
	if err {
		ctx.String(http.StatusNotFound, MISSING_PARAMETER)
		return
	}

	found, user := DB_FindUserByPassword(username, password)

	if !found {
		ctx.String(http.StatusUnauthorized, INVALID_CREDENTIALS)
		return
	}

	ctx.JSON(http.StatusAccepted, user.ID)
}

func byToken(ctx *gin.Context) {
	username, token, err := GetUsernameAndToken(ctx)
	fmt.Printf("%s, %s\n", username, token)
	if err {
		ctx.String(http.StatusNotFound, MISSING_PARAMETER)
		return
	}

	found, user := DB_FindUserByToken(username, token)

	if !found {
		ctx.String(http.StatusUnauthorized, INVALID_CREDENTIALS)
		return
	}

	ctx.JSON(http.StatusAccepted, user.ID)
}
