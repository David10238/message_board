package main

import (
	"fmt"

	"github.com/gin-gonic/gin"
)

func main() {
	const PORT = ":5001"
	fmt.Printf("Luanching auth microservice on port %s\n", PORT)
	server := gin.Default()
	Routes(server)
	server.Run(PORT)
}
