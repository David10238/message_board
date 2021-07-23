#[post("/addUser")]
pub fn add_user() -> &'static str {
    "endpoint not implemented" //TODO implement
}

#[delete("/deleteUser")]
pub fn delete_user() -> &'static str {
    "endpoint not implemented" //TODO implement
}

#[get("/doesNameExist/<name>")]
pub fn does_name_exist(name: &str) -> &'static str {
    "endpoint not implemented" //TODO implement
}

#[get("/idByToken")]
pub fn id_by_token() -> &'static str {
    "endpoint not implemented" //TODO implement
}

#[get("/userByToken")]
pub fn user_by_token() -> &'static str {
    "endpoint not implemented" //TODO implement
}

#[get("/idByPassword")]
pub fn id_by_password() -> &'static str {
    "endpoint not implemented" //TODO implement
}

#[get("/userByPassord")]
pub fn user_by_passord() -> &'static str {
    "endpoint not implemented" //TODO implement
}
