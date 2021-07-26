use actix_web::{App, HttpServer};

mod auth_controller;

#[actix_web::main]
async fn main() -> std::io::Result<()> {
    HttpServer::new(|| {
        App::new()
            .service(auth_controller::create_new_user)
            .service(auth_controller::delete_user)
            .service(auth_controller::change_password)
            .service(auth_controller::refresh_token)
            .service(auth_controller::does_name_exist)
            .service(auth_controller::get_token)
    })
    .bind(("127.0.0.1", 5000))?
    .run()
    .await
}
