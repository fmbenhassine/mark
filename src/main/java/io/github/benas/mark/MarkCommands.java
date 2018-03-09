package io.github.benas.mark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class MarkCommands {

	private BookmarkDao bookmarkDao;

	@Autowired
	public MarkCommands(BookmarkDao bookmarkDao) {
		this.bookmarkDao = bookmarkDao;
	}

	@ShellMethod("List all bookmarks")
	public List<Bookmark> list() {
		return bookmarkDao.getAllBookmarks();
	}

	@ShellMethod("Remove a bookmark")
	public String remove(int id) {
		bookmarkDao.remove(id);
		return "bookmark deleted";
	}

	@ShellMethod("Add a bookmark")
	public String add(String name, String url) {
		bookmarkDao.add(name, url);
		return "bookmark added";
	}

}
