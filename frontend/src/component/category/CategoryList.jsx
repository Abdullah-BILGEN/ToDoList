//import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { withTranslation } from 'react-i18next';
import { Link, useNavigate } from 'react-router-dom'
import CategoryApi from '../../services/CategoryApi';

import "../static/categoryList.css";

// FUNCTION
function CategoryList({ t, i18n,props}) {

  // REDIRECT
  let navigate = useNavigate();

  // STATE
  const [CategoryStateApi, setCategoryStateApi] = useState([]);

  // I18N

  // USEEFFECT
  useEffect(() => {
    CategoryApi.categoryApiList()
      .then((response) => {
        console.log(response.data);
        setCategoryStateApi(response.data);
      })
      .catch((err) => { console.error(err); });

    // axios.get("http://localhost:4444/category/api/v1/list")
    //   .then((response) => {
    //     console.log(response.data);
    //     setCategoryStateApi(response.data);
    //   })
    //   .catch((err) => { console.error(err); });
  }, []);


  // LIST
  const getListCategory = (() => {
    //axios.get("http://localhost:4444/category/api/v1/list")
    CategoryApi.categoryApiList()
      .then((response) => {
        console.log(response.data);
        setCategoryStateApi(response.data);
      })
      .catch((err) => { console.error(err); });
  });

  // DELETE
  const setDeleteCategory = async (id) => {
  if (window.confirm("Silme işlemi gerçekleşsin mi  ?")) {
    try {
      await CategoryApi.categoryApiDeleteById(id);
      getListCategory();
      navigate('/category/list', { replace: true }); // Use navigate function correctly
    } catch (error) {
      console.error(error);
    }
  } else {
    alert("Silme işlemi gerçekleştirilemedi.");
  }
};

// DELETE ALL
const setDeleteAll = async () => {
  if (window.confirm("Silme işlemini gerçekleştirmek istediğinize emin misiniz!!! ?")) {
    try {
      await CategoryApi.categoryApiDeleteAll();
      getListCategory();
      navigate('/category/list', { replace: true }); // Use navigate function correctly
    } catch (error) {
      console.error(error);
    }
  } else {
    alert("Silme işlemi gerçekleştirilemedi !!");
  }
};

// DELETE COMPLETED
const setDeleteCompleted = async () => {
  if (window.confirm("Silme işlemini gerçekleştirmek istediğinize emin misiniz!!! ?")) {
    try {
      await CategoryApi.categoryApiDeleteCompleted();
      getListCategory();
      navigate('/category/list', { replace: true }); // Use navigate function correctly
    } catch (error) {
      console.error(error);
    }
  } else {
    alert("Silme işlemi gerçekleştirilemedi !!");
  }
};

  //UPDATE
  const setUpdateCategory = (data) => {
    let { id, todoContent, systemDate } = data;
    localStorage.setItem("category_update_id", id);
    localStorage.setItem("category_update_category_name", todoContent);
    localStorage.setItem("category_update_category_date", systemDate);
  }

  //VIEW
  const setViewCategory = (id) => {
    localStorage.setItem("category_view_id", id);
  }


  // STATE
  const [todoContent, setToDoContent] = useState('');
  const [error, setError] = useState();


  // categoryName de bir değişiklik olduğunda error kalkar
  useEffect(() => {
    setError(undefined)
  }, [todoContent]);


  // CREATE
  const categoryCreate = async (event) => {
    // Browser post için duruyor
    event.preventDefault();

    // Category object
    const newCategory = {
      todoContent
    }
    console.log(newCategory);

    setError(undefined);
    // API
    try {
      const response = await CategoryApi.categoryApiCreate(newCategory);
      window.location.reload();
    } catch (err) {
      //  if (err.code === '404') {
      //   setToDoContent(err.response.data.validationErrors);
      //   return
      // }
      //setError(err.response.data.message);
      setError(err.response.data.validationErrors);
    }
    // CategoryApi.categoryApiCreate(newCategory).then((response) => {
    //   if (response.status === 200) {
    //     navigate('/category/list');
    //   }
    // })
    // .catch((err) => {
    //   console.error(err);
    // });
  }


  // CHANGE
  const categoryOnChange = (event) => {
    const { name, value } = event.target;
    //console.log(`${name} => ${value}`);

    // onChange
    setToDoContent(value)
  }

  const toggleCheckbox = async (id, isChecked) => {
    try {
      // Use CategoryApi to update the todoComplete status
      const todoCompleteValue = isChecked ? 'COMPLETE' : 'INCOMPLETE';
  
      await CategoryApi.categoryApiUpdateStatus(id, { todoComplete: todoCompleteValue });
  
      // After updating, you can update your component's state or refetch the data
      getListCategory();
    } catch (error) {
      console.error(error);
    }
  };

  const [filter, setFilter] = useState("All");

  const filterData = (data) => {
    if (filter === "Done") {
      return data.filter((item) => item.todoComplete === "COMPLETE");
    } else if (filter === "Todo") {
      return data.filter((item) => item.todoComplete === "INCOMPLETE");
    } else {
      return data; 
    }
  };

  //RETURN
  return (
  <React.Fragment>
    <br></br>
    <h1 className="text-center">{t("todoinput")} </h1>
    <div className='todoinput_box'>
      <form>
        <div className="form-group">
          <div className="input-group">
            <span className="input-group-text bg-icon-color">
              <i className="fa-solid fa-book"></i>
            </span>
            <input
              type="text"
              className="form-control"
              placeholder={t('category_name')}
              required={true}
              autoFocus={true}
              id="category_data"
              name="category_data"
              onChange={categoryOnChange}
            />
          </div>
          {/* state hatayı bootstrap ile alert ekrana basma */}
          {error ? <div className="alert alert-danger" role="alert">
            {error.categoryName}
          </div> : ""}
        </div>
        <button
          type='submit'
          className="btn btn-primary mt-4 mb-1 d-flex justify-content-center todo-button"
          disabled={!true}
          onClick={categoryCreate}>
          {t('addnewTask')}
        </button>
      </form>
    </div>
    <br></br>

    <h1 className="text-center">{t("todolist")} </h1>

    <div className="button-container text-center">
        <button
          className={`btn btn-primary mx-3 list-button ${filter === "All" ? "active" : ""}`}
          onClick={() => setFilter("All")}
        >
          {t('all')}
        </button>
        <button
          className={`btn btn-primary mx-3 list-button ${filter === "Done" ? "active" : ""}`}
          onClick={() => setFilter("Done")}
        >
          {t('done')}
        </button>
        <button
          className={`btn btn-primary mx-3 list-button ${filter === "Todo" ? "active" : ""}`}
          onClick={() => setFilter("Todo")}
        >
          {t('todo')}
        </button>
      </div>
      
    <br></br><br></br>
    
    <table className="table table-hover table-responsive gfg">
      <tbody>
        {
          filterData(CategoryStateApi).map((data) =>
            <tr key={data.id} className="table-todo">

              <td style={{ textDecoration: data.todoComplete === 'COMPLETE' ? 'line-through red' : 'none' }}>
                {data.todoContent}
              </td>
              <td style={{ textDecoration: data.todoComplete === 'COMPLETE' ? 'line-through red' : 'none' }}>
                <input
                  type="checkbox"
                  checked={data.todoComplete === 'COMPLETE'}
                  onChange={() => toggleCheckbox(data.id, data.todoComplete !== 'COMPLETE')}
                />

              </td>

              <td>
                <Link to={`/category/update/${data.id}`}>
                  <i onClick={() => setUpdateCategory(data)} className="fa-solid fa-pen-to-square text-primary"></i>
                </Link>
              </td>

              <td>
                <Link to={`/category/delete}`}>
                  <i onClick={() => setDeleteCategory(data.id)} className="fa-solid fa-trash text-danger"></i>
                </Link>
              </td>
            </tr>
          )
        }
      </tbody>
    </table>

    <br></br>

    <div className="button-container text-center">
      <button className="btn btn-primary mx-3 delete-button" onClick={setDeleteCompleted}>
      {t('delete_done')}
      </button>
      <button className="btn btn-primary mx-3 delete-button" onClick={setDeleteAll}>
      {t('delete_all')}
      </button>
    </div>
    <br></br><br></br><br></br><br></br>
  </React.Fragment>
  )
}

//i18n
export default withTranslation()(CategoryList); 