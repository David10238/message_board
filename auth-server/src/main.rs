#[macro_use]
extern crate rocket;

#[get("/ping")]
fn ping() -> &'static str {
    "Pong from Rust"
}

#[launch]
fn rocket() -> _ {
    rocket::build().mount("/", routes![ping])
}
