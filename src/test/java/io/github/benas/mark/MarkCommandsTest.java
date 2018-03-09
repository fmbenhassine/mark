package io.github.benas.mark;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

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
