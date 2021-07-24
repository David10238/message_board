pub use rocket::http::Status;
pub use rocket::request::{FromRequest, Outcome, Request};

#[derive(Debug)]
pub enum ApiKeyError {
    Missing,
}

#[derive(Debug)]
pub struct Token<'r> {
    value: &'r str,
}

#[rocket::async_trait]
impl<'r> FromRequest<'r> for Token<'r> {
    type Error = ApiKeyError;

    async fn from_request(req: &'r Request<'_>) -> Outcome<Self, Self::Error> {
        let token = req.headers().get_one("token");
        match token {
            // token exists
            Some(token) => Outcome::Success(Token { value: token }),
            // token does not exist
            None => Outcome::Failure((Status::BadRequest, ApiKeyError::Missing)),
        }
    }
}

#[derive(Debug)]
pub struct UsernameAndPassword<'r> {
    username: &'r str,
    password: &'r str,
}

#[rocket::async_trait]
impl<'r> FromRequest<'r> for UsernameAndPassword<'r> {
    type Error = ApiKeyError;

    async fn from_request(req: &'r Request<'_>) -> Outcome<Self, Self::Error> {
        let username = req.headers().get_one("username");
        let password = req.headers().get_one("password");

        if username.is_some() && password.is_some() {
            return Outcome::Success(UsernameAndPassword {
                username: username.unwrap(),
                password: password.unwrap(),
            });
        }
        Outcome::Failure((Status::BadRequest, ApiKeyError::Missing))
    }
}

#[post("/addUser")]
pub fn add_user(auth: UsernameAndPassword) -> &'static str {
    "endpoint not implemented" //TODO implement
}

#[delete("/deleteUser")]
pub fn delete_user(auth: UsernameAndPassword) -> &'static str {
    "endpoint not implemented" //TODO implement
}

#[get("/doesNameExist/<name>")]
pub fn does_name_exist(name: &str) -> &'static str {
    "endpoint not implemented" //TODO implement
}

#[get("/idByToken")]
pub fn id_by_token(token: Token) -> String {
    "endpoint not implemented".to_string() + token.value //TODO implement
}

#[get("/userByToken")]
pub fn user_by_token(token: Token) -> &'static str {
    "endpoint not implemented" //TODO implement
}

#[get("/idByPassword")]
pub fn id_by_password(auth: UsernameAndPassword) -> String {
    "endpoint not implemented".to_string() + auth.username + auth.password //TODO implement
}

#[get("/userByPassord")]
pub fn user_by_passord(auth: UsernameAndPassword) -> &'static str {
    "endpoint not implemented" //TODO implement
}
