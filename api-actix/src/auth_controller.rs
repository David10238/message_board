use actix_web::{delete, get, patch, post, HttpResponse, Responder};

#[post("/auth/create")]
async fn create_new_user() -> impl Responder {
    //todo implement
    HttpResponse::Ok().body("a")
}

#[delete("/auth/delete")]
async fn delete_user() -> impl Responder {
    //todo implement
    HttpResponse::Ok().body("b")
}

#[patch("/auth/changePassword")]
async fn change_password() -> impl Responder {
    //todo implement
    HttpResponse::Ok().body("c")
}

#[patch("/auth/refreshToken")]
async fn refresh_token() -> impl Responder {
    //todo implement
    HttpResponse::Ok().body("d")
}

#[get("/auth/doesNameExist")]
async fn does_name_exist() -> impl Responder {
    //todo implement
    HttpResponse::Ok().body("e")
}

#[get("/auth/token")]
async fn get_token() -> impl Responder {
    //todo implement
    HttpResponse::Ok().body("Hello")
}
