from django.http import HttpRequest
from typing import List, Mapping

# codes are in ISO 639-2/B


class LanguageCodes:
    ENGLISH: str = "eng"
    SPANISH: str = "spa"
    ALL_CODES: list[str] = [ENGLISH, SPANISH]

    @staticmethod
    def is_valid(code: str):
        return code in LanguageCodes.ALL_CODES


class Headers:
    ID: str = "id"

    FIRST_NAME: str = "firstName"
    LAST_NAME: str = "lastName"
    BIO: str = "bio"

    DARK_MODE: str = "darkMode"
    LANGUAGE: str = "language"

    @staticmethod
    def is_ison(req: HttpRequest):
        return "Content-Type" in req.headers and req.headers["Content-Type"] == "application/json"


class Messages:
    MISSING_HEADER = "MISING_HEADER"
    WRONG_METHOD = "WRONG_METHOD"
    MALFORMED_JSON = "MALFORMED_JSON"
    NO_CHANGES = "NO_CHANGES"
