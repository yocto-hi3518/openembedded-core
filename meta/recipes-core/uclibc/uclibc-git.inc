SRCREV = "3a8c7f84da5e4dba9315608ee5e932399c657b60"

PV = "1.0.30+git${SRCPV}"

FILESEXTRAPATHS =. "${FILE_DIRNAME}/uclibc-git:"

SRC_URI = "git://uclibc-ng.org/git/uclibc-ng \
        file://0001-Revert-ldconfig-add-glibc-compatibility-fix.patch \
        file://0002-librt-declare-clock_nanosleep-independent-of-thread-.patch \
        file://0003-ldso-arc-fix-LD_DEBUG-segv-when-printing-R_ARC_NONE.patch \
        file://uClibc.machine \
        file://uClibc.distro \
        file://obstack.cfg \
        file://locale.cfg \
"
S = "${WORKDIR}/git"

UPSTREAM_CHECK_GITTAGREGEX = "(?P<pver>\d+\.\d+(\.\d+)*)"

