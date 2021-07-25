mod controller;

use actix_web::{App, HttpServer};

#[actix_web::main]
async fn main() -> std::io::Result<()> {
    HttpServer::new(|| {
        App::new()
            .service(controller::deleteUser)
            .service(controller::addUser)
            .service(controller::doesNameExist)
            .service(controller::changeUserPassword)
            .service(controller::userByPassword)
            .service(controller::idByPassword)
            .service(controller::userByToken)
            .service(controller::idByToken)
    })
    .bind("127.0.0.1:5001")?
    .run()
    .await
}
