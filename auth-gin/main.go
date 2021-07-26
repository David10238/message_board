package main

import (
	"fmt"

	"github.com/gin-gonic/gin"
)

func main() {
	fmt.Println("Luanching auth microservice")

	server := gin.Default()
	Routes(server)
	server.Run(":5001")
}
