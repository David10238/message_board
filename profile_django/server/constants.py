# codes are in ISO 639-2/B
class LanguageCodes:
    ENGLISH: str = "eng"
    SPANISH: str = "spa"
    ALL_CODES: list[str] = [ENGLISH, SPANISH]

    @staticmethod
    def is_valid(code: str):
        return code in ALL_CODES


class Headers:
    ID = "id"

class Messages:
    MISSING_HEADER = "MISING_HEADER"