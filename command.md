# Install de repo

```bash
mkdir ~/bin
PATH=~/bin:$PATH
curl https://storage.googleapis.com/git-repo-downloads/repo > ~/bin/repo
chmod a+x ~/bin/repo
```

# Initialisation d'un repo android 

```bash
mkdir AOSP
cd AOSP
repo init -u https://android.googlesource.com/platform/manifest -b android-10.0.0_r1
repo sync -j8 -c
```