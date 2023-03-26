package ylab_homework_04.filesort;

import java.io.File;
import java.sql.SQLException;

public interface FileSorter {
  File sort(File data) throws SQLException;
}
