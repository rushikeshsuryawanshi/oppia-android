package org.oppia.util.profile

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton
import java.io.File

/** Utility to manage creation and deletion of directories. */
@Singleton
class DirectoryManagementUtil @Inject constructor(private val context: Context) {

  /**
   * Gets or creates a directory associated with the given profileId.
   *
   * @param profileId name of the directory to be returned.
   * @return the directory with the name specified by profileId.
   */
  fun getOrCreateDir(profileId: String): File {
    return context.getDir(profileId, Context.MODE_PRIVATE)
  }

  /**
   * Deletes a directory with the name specified by profileId.
   *
   * @param profileId name of directory to be deleted.
   * @return whether directory was successfully deleted.
   */
  fun deleteDir(profileId: String): Boolean {
    return deleteRecursive(getOrCreateDir(profileId))
  }

  /** Directories must be empty first before deleting */
  private fun deleteRecursive(file: File): Boolean {
    if (file.isDirectory) {
      file.listFiles().forEach {
        deleteRecursive(it)
      }
    }
    return file.delete()
  }
}
