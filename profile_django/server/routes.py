
from django.urls import path
from django.http import HttpResponse, HttpRequest, JsonResponse, HttpResponseNotFound
from django.views.decorators.http import require_http_methods, require_GET, require_POST
from .models import Profile
from .constants import LanguageCodes, Headers, Messages
from typing import Mapping, List, Type, Tuple
import json


def has_any(m: Mapping[str, any], keys: List[Tuple[str, Type]]) -> bool:
    for key in keys:
        if has_typed_key(m, key[0], key[1]):
            return True
    return False


def has_typed_key(m: Mapping[str, any], key: str, t: Type):
    return key in m.keys() and isinstance(m[key], t)


def has_int(m: Mapping[str, any], key: str) -> bool:
    return key in m.keys() and m[key].isnumeric()


def has_json_bool(m: Mapping[str, any], key: str) -> bool:
    return True


@require_POST
def add_user(req: HttpRequest):
    if not (has_int(req.headers, Headers.ID) and Headers.FIRST_NAME in req.headers.keys() and Headers.LAST_NAME in req.headers.keys()):
        return HttpResponseNotFound(Messages.MISSING_HEADER)

    prof = Profile(
        id=req.headers[Headers.ID],
        first_name=req.headers[Headers.FIRST_NAME],
        last_name=req.headers[Headers.LAST_NAME],
        bio="",
        dark_mode=True,
        language=LanguageCodes.ENGLISH
    )
    prof.save()

    return HttpResponse()


@require_http_methods(["DELETE"])
def delete_user(req: HttpRequest):
    if not has_int(req.headers, Headers.ID):
        return HttpResponseNotFound(Messages.MISSING_HEADER)

    Profile.objects.filter(id=req.headers[Headers.ID]).delete()
    return HttpResponse("delete")


@require_GET
def get_profile(req: HttpRequest):
    if not has_int(req.headers, Headers.ID):
        return HttpResponseNotFound(Messages.MISSING_HEADER)

    prof = Profile.objects.get(id=req.headers[Headers.ID])
    data = {
        Headers.ID: prof.id,
        Headers.FIRST_NAME: prof.first_name,
        Headers.LAST_NAME: prof.last_name,
        Headers.BIO: prof.bio,
        Headers.DARK_MODE: prof.dark_mode,
        Headers.LANGUAGE: prof.last_name
    }
    return JsonResponse(data)


@require_GET
def get_short_profile(req: HttpRequest):
    if not has_int(req.headers, Headers.ID):
        return HttpResponseNotFound(Messages.MISSING_HEADER)

    prof = Profile.objects.get(id=req.headers[Headers.ID])
    data = {
        Headers.ID: prof.id,
        Headers.FIRST_NAME: prof.first_name,
        Headers.LAST_NAME: prof.last_name
    }
    return JsonResponse(data)


@require_http_methods(["PATCH"])
def merge_changes(req: HttpRequest):
    if not has_int(req.headers, Headers.ID):
        return HttpResponseNotFound(Messages.MISSING_HEADER)

    if not Headers.is_ison(req):
        return HttpResponseNotFound(Messages.MISSING_HEADER)

    try:
        data: Mapping[str, any] = json.loads(req.body)
    except ValueError:
        return HttpResponseNotFound(Messages.MALFORMED_JSON)

    hasNormalSetting = has_any(data, [
        (Headers.FIRST_NAME, str),
        (Headers.LAST_NAME, str),
        (Headers.BIO, str),
        (Headers.DARK_MODE, bool)
    ])

    hasLanguageSetting = has_typed_key(
        data, Headers.LANGUAGE, str) and LanguageCodes.is_valid(data[Headers.LANGUAGE])

    if not (hasNormalSetting or hasLanguageSetting):
        return HttpResponse(Messages.NO_CHANGES)

    shouldChange = False

    prof = Profile.objects.get(id=req.headers[Headers.ID])
    # check first name
    if has_typed_key(data, Headers.FIRST_NAME, str) and data[Headers.FIRST_NAME] != prof.first_name:
        prof.first_name = data[Headers.FIRST_NAME]
        shouldChange = True
    # check last name
    if has_typed_key(data, Headers.LAST_NAME, str) and data[Headers.LAST_NAME] != prof.last_name:
        prof.last_name = data[Headers.LAST_NAME]
        shouldChange = True
    # check bio
    if has_typed_key(data, Headers.BIO, str) and data[Headers.BIO] != prof.bio:
        prof.bio = data[Headers.BIO]
        shouldChange = True
    # check dark mode
    if has_typed_key(data, Headers.DARK_MODE, bool) and data[Headers.DARK_MODE] != prof.dark_mode:
        prof.dark_mode = data[Headers.DARK_MODE]
        shouldChange = True
    # check language
    if(hasLanguageSetting and data[Headers.LANGUAGE] != prof.language):
        prof.language = data[Headers.LANGUAGE]
        shouldChange = True

    if not shouldChange:
        return HttpResponse(Messages.NO_CHANGES)
    prof.save()
    return HttpResponse()


urlpatterns = [
    path('add', add_user),
    path('delete', delete_user),
    path('profile', get_profile),
    path('short', get_short_profile),
    path("merge", merge_changes)


]
