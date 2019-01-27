SUMMARY = "Systemd system configuration"
DESCRIPTION = "Systemd may require slightly different configuration for \
different machines.  For example, qemu machines require a longer \
DefaultTimeoutStartSec setting."
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "/usr/lib/journald.conf.d/* \
	/usr/lib/logind.conf.d/* \
	/usr/lib/system.conf.d/* \
	/etc/systemd/* \
"

do_install() {
	install -d ${D}/usr/lib/journald.conf.d
	# Enable journal to forward message to syslog daemon
	echo "ForwardToSyslog=yes" >> ${D}/usr/lib/journald.conf.d/00-${PN}.conf
	# Set the maximium size of runtime journal to 64M as default
	echo "RuntimeMaxUse=64M" >> ${D}/usr/lib/journald.conf.d/00-${PN}.conf

	install -d ${D}${sysconfdir}/systemd/journald.conf.d
	ln -s ../journald.conf ${D}${sysconfdir}/systemd/journald.conf.d/00-${PN}.conf

	install -d ${D}/usr/lib/logind.conf.d
	# Set KILL_USER_PROCESSES to yes
	echo "KillUserProcesses=yes" >> ${D}/usr/lib/logind.conf.d/00-${PN}.conf

	install -d ${D}${sysconfdir}/systemd/logind.conf.d
	ln -s ../logind.conf ${D}${sysconfdir}/systemd/logind.conf.d/00-${PN}.conf

	install -d ${D}/usr/lib/system.conf.d
	# Set MEMORY_ACCOUNTING_DEFAULT to yes
	echo "DefaultMemoryAccounting=yes" >> ${D}/usr/lib/system.conf.d/00-${PN}.conf

	install -d ${D}${sysconfdir}/systemd/system.conf.d
	ln -s ../system.conf ${D}${sysconfdir}/systemd/system.conf.d/00-${PN}.conf
}

# Based on change from YP bug 8141, OE commit 5196d7bacaef1076c361adaa2867be31759c1b52
do_install_append_qemuall() {
	# Change DefaultTimeoutStartSec from 90s to 240s
	echo "DefaultTimeoutStartSec = 240s" >> ${D}/usr/lib/system.conf.d/00-${PN}.conf
}
