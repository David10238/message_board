
from django.urls import path
from django.http import HttpResponse, HttpRequest, JsonResponse
from .models import Profile
from .constants import LanguageCodes


def add_user(req: HttpRequest):
    prof = Profile(
        id=0,
        first_name="Name1",
        last_name="Name2",
        bio="Bio",
        dark_mode=True,
        language=LanguageCodes.ENGLISH
    )
    prof.save()
    return HttpResponse("")


def delete_user(req: HttpRequest):
    return HttpResponse("delete")


def get_profile(req: HttpRequest):
    user_id = 0
    prof = Profile.objects.get(id=user_id)
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
    user_id = 0
    prof = Profile.objects.get(id=user_id)
    data = {
        "id": prof.id,
        "firstName": prof.first_name,
        "lastName": prof.last_name
    }
    return JsonResponse(data)


def merge_changes(req: HttpRequest):
    return HttpResponse("merge")


urlpatterns = [
    path('add', add_user),
    path('delete', delete_user),
    path('profile', get_profile),
    path('short', get_short_profile),
    path("merge", merge_changes)
]
