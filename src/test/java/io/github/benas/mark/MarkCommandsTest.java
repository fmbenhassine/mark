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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

/**
 * @author Mahmoud Ben Hassine
 */
public class MarkCommandsTest {

	private BookmarkDao bookmarkDao;

	private MarkCommands markCommands;

	@Before
	public void setUp() {
		bookmarkDao = Mockito.mock(BookmarkDao.class);
		markCommands = new MarkCommands(bookmarkDao);
	}

	@Test
	public void testListCommand() {
		Bookmark bookmark = new Bookmark();
		bookmark.setId(1);
		bookmark.setName("google");
		bookmark.setUrl("http://www.google.com");
		Mockito.when(bookmarkDao.getAllBookmarks()).thenReturn(Collections.singletonList(bookmark));

		List<Bookmark> bookmarks = markCommands.list();

		Assert.assertEquals(1, bookmarks.size());
		Assert.assertEquals(bookmark, bookmarks.get(0));
	}

	@Test
	public void testDeleteCommand() {
		markCommands.remove(1);

		Mockito.verify(bookmarkDao).remove(1);
	}

	@Test
	public void testAddCommand() {
		markCommands.add("google", "http://www.google.com");

		Mockito.verify(bookmarkDao).add("google", "http://www.google.com");
	}
}
