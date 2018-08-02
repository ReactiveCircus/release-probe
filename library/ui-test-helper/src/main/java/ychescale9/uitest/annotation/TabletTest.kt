package ychescale9.uitest.annotation

/**
 * Annotation to indicate a test should only be run on tablets
 */
@Retention(value = AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class TabletTest
