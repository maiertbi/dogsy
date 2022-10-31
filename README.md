# Dogsy

## colors

All colors can be found in res > values > colors.xml. 
In order to use them you have to type in @color/color_needed (ex. @color/primaryColor).

## android material

The dependency for android material has been added. It's best if you check it out in the web [Android materials](https://m2.material.io/components?platform=android).
It can be really useful when coding in Android Studio and if you need to implement anything specifical.
It would be also helpful to do look for a Youtube Tutorial in order to understand Android Materials.

## id naming convention

When you are naming buttons, textviews, etc. there is a small naming convention to follow.

**datatype + _ + descriptor**

Datatypes:
- TextView = text_
- Button = button_
- Imageview = image_
- Menu = menu_
- ...

So some examples would be:
- button_logIn
- text_email
- text_password
- text_confirmPassword
- ...

## using gitHub

Github has a specific workflow. In order to not mess up any project files it has to be done correctly!
**You have to understand the concept of github!** (branches, merge requests, ...)

When you are going to work on an issue do the following:
- *git branch* (to see on which branch you are on)
- change to main branch via *git checkout main*
- update the main branch with the latest changes via *git pull* and check if it worked
- create a new branch via *git checkout -b newBranchName* 
  - **name the branch after the issue/activity you are currently working on**
- do some changes to the code
- **commit every small thing you did and describe it so that everyone can understand what you did**
- when finished, push the new branch
- go to [github dogsy](https://github.com/maiertbi/dogsy.git) and make a pull request
  - **only make a pull request if your code is working perfectly fine**
  - if it passes every test and there are no errors --> pull/merge your branch into the main branch
