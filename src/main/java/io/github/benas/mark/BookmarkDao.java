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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Mahmoud Ben Hassine
 */

@Component
public class BookmarkDao {

	private RowMapper<Bookmark> bookmarkRowMapper = new BookmarkRowMapper();

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public BookmarkDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@PostConstruct
	private void applyDDL() {
		jdbcTemplate.update("create table if not exists bookmark (id int not null primary key, name varchar, url varchar)");
	}

	public List<Bookmark> getAllBookmarks() {
		return jdbcTemplate.query("select * from bookmark", bookmarkRowMapper);
	}

	public Bookmark getById(int id) {
		return jdbcTemplate.queryForObject("select * from bookmark where id = ?", new Object[]{id}, bookmarkRowMapper);
	}

	public void remove(int bookmarkId) {
		jdbcTemplate.update("delete from bookmark where id = ?", bookmarkId);
	}

	public void add(String name, String url) {
		int id;
		try {
			id = jdbcTemplate.queryForObject("select max(id) from bookmark", Integer.class);
		} catch (Exception e) { // sqlite returns null when the table is empty (=> NPE)
			id = 0;
		}
		jdbcTemplate.update("insert into bookmark values (?, ?, ?)", id + 1, name, url);
	}

	private class BookmarkRowMapper implements RowMapper<Bookmark> {
		@Override
		public Bookmark mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Bookmark bookmark = new Bookmark();
			bookmark.setId(resultSet.getInt(1));
			bookmark.setName(resultSet.getString(2));
			bookmark.setUrl(resultSet.getString(3));
			return bookmark;
		}
	}
}
