/**
 * The MIT License
 *
 * Copyright (c) 2018, Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.github.benas.mark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

/**
 * @author Mahmoud Ben Hassine
 */

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
	
	@ShellMethod("Browse a bookmark")
	public void browse(int id) {
		Bookmark bookmark = bookmarkDao.getById(id);
		if (bookmark != null) {
			String url = bookmark.getUrl();
			try {
				Desktop.getDesktop().browse(new URI(url));
			} catch (IOException | URISyntaxException e) {
				System.err.println("Unable to open bookmark for browsing at url: " + url);
			}
		}
	}

}
