from django.db import models


class Profile(models.Model):
    id = models.BigIntegerField
    first_name = models.TextField()
    last_name = models.TextField()
    bio = models.TextField()
    dark_mode = models.BooleanField()
    language = models.CharField(max_length=3)
