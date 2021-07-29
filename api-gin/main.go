package main

import (
	"fmt"

	"github.com/gin-gonic/gin"
)

func main() {
	const PORT = ":5000"
	fmt.Printf("Luanching API on port %s\n", PORT)
	server := gin.Default()
	AuthRoutes(server)
	server.Run(PORT)
}
