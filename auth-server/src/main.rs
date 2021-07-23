mod controller;

#[macro_use]
extern crate rocket;

pub fn a() {}

#[launch]
fn rocket() -> _ {
    rocket::build().mount(
        "/",
        routes![
            controller::add_user,
            controller::delete_user,
            controller::does_name_exist,
            controller::id_by_token,
            controller::user_by_token,
            controller::id_by_password,
            controller::user_by_passord
        ],
    )
}
