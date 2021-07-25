import express from "express";

const app = express();
const port = 5000;

app.get("/", (req, res) => {
  res.status(200).send(`You sent a message with body:\n${req.body}`);
});

app.listen(port, () => console.log(`Running on port ${port}`));
