package com.example.Todoapp.data.repository;

import com.example.Todoapp.data.entity.TodoInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class TodoInfoRepository implements ITodoInfoRepository{

    private static final String FIND_ALL_SQL = "select * from todos";
    private static final String FIND_BY_ID = "select * from todos where todo_id = :id";
    private static final String FIND_BY_TITLE_LIKE = "select * from todos where title like ";
    private static final String DELETE_BY_ID = "delete from todos where todo_id = :id";
    private static final String DELETE_ID_BETWEEN = "delete from todos where todo_id between :start and :end";
    private static final String COUNT_SQL = "select count(*) from todos";
    private static final String DEADLINE_SQL = "select * from todos where completed = false order by expected_end_date limit :limit";
    private static final String FIND_BY_MONTH_BETWEEN = "select * from todos where extract(MONTH from start_date) between :start and :end";
    private static final String SAVE_SQL = "insert into  todos (username, title, description, insert_date, start_date, expected_end_date, completed)\n" +
            "values (:username, :title, :description, :insertDate, :startDate, :expectedEndDate, :completed)";

    private final NamedParameterJdbcTemplate m_namedParameterJdbcTemplate;

    public TodoInfoRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        m_namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public static void fillTodos(ResultSet resultSet, ArrayList<TodoInfo> todoInfos) throws SQLException
    {
        do {
            var id = resultSet.getLong(1);
            var username = resultSet.getString(2);
            var title = resultSet.getString(3);
            var description = resultSet.getString(4);
            var insertDate = resultSet.getDate(5).toLocalDate();
            var startDate = resultSet.getDate(6).toLocalDate();
            var expectedEndDate = resultSet.getDate(7).toLocalDate();
            var endDate = resultSet.getDate(8);
            var completed = resultSet.getBoolean(9);

            todoInfos.add(new TodoInfo(id, username, title, description, insertDate, startDate,
                    expectedEndDate, endDate == null ? null : endDate.toLocalDate(), completed));
        }while (resultSet.next());

    }

    @Override
    public Iterable<TodoInfo> findAll() {

        ArrayList<TodoInfo> todoInfos = new ArrayList<>();

        m_namedParameterJdbcTemplate.query(FIND_ALL_SQL, (ResultSet resultSet) -> fillTodos(resultSet, todoInfos));

        return todoInfos;
    }

    @Override
    public void deleteById(Long id) {

        Map<String, Long> map = new HashMap<>();

        map.put("id",id);

        m_namedParameterJdbcTemplate.update(DELETE_BY_ID, map);

    }
    @Override
    public void deleteIdBetween(int start, int end) {

        Map<String, Integer> map = new HashMap<>();

        map.put("start", start);
        map.put("end", end);

        m_namedParameterJdbcTemplate.update(DELETE_ID_BETWEEN, map);
    }

    @Override
    public Iterable<TodoInfo> findByMonthBetween(int start, int end) {

        ArrayList<TodoInfo> todoInfos = new ArrayList<>();

        Map<String, Integer> parameterMap = new HashMap<>();

        parameterMap.put("start", start);
        parameterMap.put("end", end);

        m_namedParameterJdbcTemplate.query(FIND_BY_MONTH_BETWEEN, parameterMap, (ResultSet result) -> fillTodos(result, todoInfos));

        return todoInfos;

    }

    @Override
    public Iterable<TodoInfo> closestTodoDeadline(int todos) {

        ArrayList<TodoInfo> todoInfos = new ArrayList<>();

        Map<String, Integer> map = new HashMap<>();

        map.put("limit", todos);

        m_namedParameterJdbcTemplate.query(DEADLINE_SQL, map, (ResultSet result) -> fillTodos(result, todoInfos));

        return todoInfos;
    }

    @Override
    public Optional<TodoInfo> findById(Long id) {

        ArrayList<TodoInfo> todoInfos = new ArrayList<>();

        Map<String, Long> map = new HashMap<>();

        map.put("id", id);

        m_namedParameterJdbcTemplate.query(FIND_BY_ID, map, (ResultSet resultset) -> fillTodos(resultset, todoInfos));

        return todoInfos.stream().findFirst();
    }

    @Override
    public Iterable<TodoInfo> findByTitleLike(String title) {

        Map<String, String> map = new HashMap<>();

        ArrayList<TodoInfo> todoInfoArrayList = new ArrayList<>();

        map.put("letters", title);

        m_namedParameterJdbcTemplate.query(FIND_BY_TITLE_LIKE, map, (ResultSet result) -> fillTodos(result, todoInfoArrayList));

        return todoInfoArrayList;
    }

    @Override
    public <S extends TodoInfo> S save(S entity) {

        var keyHolder = new GeneratedKeyHolder();

        m_namedParameterJdbcTemplate.update(SAVE_SQL, new BeanPropertySqlParameterSource(entity), keyHolder, new String[] {"todo_id"});

        entity.setId(keyHolder.getKey().intValue());

        return entity;
    }

    @Override
    public long count() {

        ArrayList<Integer> arrayList = new ArrayList<>();

        m_namedParameterJdbcTemplate.query(COUNT_SQL, (ResultSet result) ->
        {
            arrayList.add(result.getInt(1));
        }
        );

        return arrayList.get(0);
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }
    @Override
    public void deleteAll(Iterable entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable save(Iterable entities) {
        throw new UnsupportedOperationException();
    }
    @Override
    public void delete(TodoInfo entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable findAllById(Iterable iterable) {
        throw new UnsupportedOperationException();
    }
    @Override
    public boolean exitsById(Long aLong) {
        throw new UnsupportedOperationException();
    }
}
