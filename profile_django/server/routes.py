
from django.urls import path
from django.http import HttpResponse, HttpRequest, JsonResponse, HttpResponseNotFound
from .models import Profile
from .constants import LanguageCodes, Headers, Messages
from typing import Mapping


def has_int(m: Mapping[str, any], key: str) -> bool:
    return key in m and m[key].isnumeric()


def has_bool(m: Mapping[str, any], key: str) -> bool:
    return True


def add_user(req: HttpRequest):
    if not has_int(req.headers, Headers.ID):
        return HttpResponseNotFound(Messages.MISSING_HEADER)

    prof = Profile(
        id=req.headers[Headers.ID],
        first_name="Name1",
        last_name="Name2",
        bio="Bio",
        dark_mode=True,
        language=LanguageCodes.ENGLISH
    )
    prof.save()

    return HttpResponse()


def delete_user(req: HttpRequest):
    if not has_int(req.headers, Headers.ID):
        return HttpResponseNotFound(Messages.MISSING_HEADER)

    Profile.objects.filter(id=req.headers[Headers.ID]).delete()
    return HttpResponse("delete")


def get_profile(req: HttpRequest):
    if not has_int(req.headers, Headers.ID):
        return HttpResponseNotFound(Messages.MISSING_HEADER)

    prof = Profile.objects.get(id=req.headers[Headers.ID])
    data = {
        "id": prof.id,
        "firstName": prof.first_name,
        "lastName": prof.last_name,
        "bio": prof.bio,
        "dark_mode": prof.dark_mode,
        "language": prof.language
    }
    return JsonResponse(data)


def get_short_profile(req: HttpRequest):
    if not has_int(req.headers, Headers.ID):
        return HttpResponseNotFound(Messages.MISSING_HEADER)

    prof = Profile.objects.get(id=req.headers[Headers.ID])
    data = {
        "id": prof.id,
        "firstName": prof.first_name,
        "lastName": prof.last_name
    }
    return JsonResponse(data)


def merge_changes(req: HttpRequest):
    if not has_int(req.headers, Headers.ID):
        return HttpResponseNotFound(Messages.MISSING_HEADER)

    return HttpResponse("merge")


urlpatterns = [
    path('add', add_user),
    path('delete', delete_user),
    path('profile', get_profile),
    path('short', get_short_profile),
    path("merge", merge_changes)
]
