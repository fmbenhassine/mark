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
