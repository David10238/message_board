use actix_web::{delete, get, post, put, web, HttpResponse, Responder};

#[delete("/deleteUser")]
async fn deleteUser() -> impl Responder {
    HttpResponse::Ok().body("Not implemented")
}

#[post("/addUser")]
async fn addUser() -> impl Responder {
    HttpResponse::Ok().body("Not implemented")
}

#[get("/doesNameExist")]
async fn doesNameExist() -> impl Responder {
    HttpResponse::Ok().body("Not implemented")
}

#[put("/changeUserPassword")]
async fn changeUserPassword() -> impl Responder {
    HttpResponse::Ok().body("Not implemented")
}

#[get("/userByPassword")]
async fn userByPassword() -> impl Responder {
    HttpResponse::Ok().body("Not implemented")
}

#[get("/idByPassword")]
async fn idByPassword() -> impl Responder {
    HttpResponse::Ok().body("Not implemented")
}

#[get("/userByToken")]
async fn userByToken() -> impl Responder {
    HttpResponse::Ok().body("Not implemented")
}

#[get("/idByToken")]
async fn idByToken() -> impl Responder {
    HttpResponse::Ok().body("Not implemented")
}
