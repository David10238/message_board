package main

import (
	"fmt"

	"github.com/gin-gonic/gin"
)

func main() {
	fmt.Println("Luanching auth microservice")

	server := gin.Default()
	server.Run(":5001")
}
