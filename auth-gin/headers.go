package main

import (
	"strings"

	"github.com/gin-gonic/gin"
)

const MISSING_PARAMETER = "MISSING_PARAMETER"
const INVALID_CREDENTIALS = "INVALID_CREDENTIALS"

func GetUsernameAndPassword(ctx *gin.Context) (string, string, bool) {
	username, errUsername := GetUsername(ctx)
	password, errPassowrd := GetPassword(ctx)
	if errUsername || errPassowrd {
		return "", "", true
	}
	return username, password, false
}

func GetUsernameAndToken(ctx *gin.Context) (string, string, bool) {
	username, errUsername := GetUsername(ctx)
	token, errToken := GetToken(ctx)
	if errUsername || errToken {
		return "", "", true
	}
	return username, token, false
}

func GetToken(ctx *gin.Context) (string, bool) {
	return GetString(ctx, "token")
}

func GetNewPassword(ctx *gin.Context) (string, bool) {
	return GetString(ctx, "newPassword")
}

func GetPassword(ctx *gin.Context) (string, bool) {
	return GetString(ctx, "password")
}

func GetUsername(ctx *gin.Context) (string, bool) {
	str, b := GetString(ctx, "username")
	return strings.ToLower(str), b
}

func GetStringArray(ctx *gin.Context, key string) ([]string, bool) {
	arr, ok := ctx.Request.Header[strings.Title(strings.ToLower(key))]
	if !ok {
		return make([]string, 0), true
	}
	return arr, false
}

func GetString(ctx *gin.Context, key string) (string, bool) {
	arr, err := GetStringArray(ctx, key)
	if err || len(arr) != 1 {
		return "", true
	}
	return arr[0], false
}
