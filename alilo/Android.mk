LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_STATIC_JAVA_LIBRARIES := android-support-v4	\
	ar_core	\
	fastjson	\
	gson	\
	annotations	\
	jackson-core	\
	jackson-databind  \
	universal-image-loader	



LOCAL_SRC_FILES := $(call all-java-files-under, src)
LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/res


LOCAL_PACKAGE_NAME := alilo
LOCAL_CERTIFICATE := platform
LOCAL_PRIVILEGED_MODULE := true

include $(BUILD_PACKAGE)
####################################
include $(CLEAR_VARS)

LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES :=ar_core:ar_core.jar	\
		fastjson:fastjson-1.1.37.jar	\
		gson:gson-2.2.4.jar	\
		annotations:jackson-annotations-2.4.0.jar	\
		jackson-core:jackson-core-2.4.1.jar	\
		jackson-databind:jackson-databind-2.4.1.jar		\
		universal-image-loader:universal-image-loader-1.9.2-SNAPSHOT-with-sources.jar	


# Use the folloing include to make our test apk.
include $(BUILD_MULTI_PREBUILT)
include $(call all-makefiles-under,$(LOCAL_PATH))
