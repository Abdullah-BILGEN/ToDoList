// REACT
import React, { useEffect, useState } from 'react'

// LANGUAGE
import { withTranslation } from 'react-i18next'

// ROUTER 
import { useNavigate } from 'react-router-dom'

// API
import CategoryApi from '../../services/CategoryApi';

// FUNCTION
function CategoryCreate({ t }) {

  // REDIRECT
  const navigate = useNavigate();

  // STATE
  const [categoryName, setCategoryName] = useState('');
  const [error, setError] = useState();


  // categoryName değişiklik olduğunda error kalkar
  useEffect(() => {
    setError(undefined)
  }, [categoryName]);


  // CREATE
  const categoryCreate = async (event) => {
    // Browser'ın post için duruyor
    event.preventDefault();

    // Category object
    const newCategory = {
      categoryName
    }
    console.log(newCategory);

    setError(undefined);
    // API
    try {
      const response = await CategoryApi.categoryApiCreate(newCategory);
    } catch (err) {
      //  if (err.code === '404') {
      //   setCategoryName(err.response.data.validationErrors);
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
    setCategoryName(value)
  }


  // RETURN
  return (
    <React.Fragment>
      <form>
        <h2 className="display-3 mt-4">{t('category_name')}</h2>
        <div className="form-group">
          <span>{t('category_name')}</span>
          <input
            type="text"
            className="form-control"
            placeholder={t('category_name')}
            required={true}
            autoFocus={true}
            id="category_data"
            name="category_data"
            onChange={categoryOnChange}
          //onChange={(event)=>{setCategoryName(event.target.value)}}
          />
          {/* state hatayı bootstrap ile alert ekrana basma */}
          {error ? <div className="alert alert-danger" role="alert">
            {error.categoryName}
          </div> : ""}
        </div>
        <button
          type='submit'
          className="btn btn-primary mt-3"
          disabled={!true}
          onClick={categoryCreate}>{t('create')}</button>
      </form>
      <br /><br /><br /><br /><br /><br /><br /><br />
    </React.Fragment>
  ) //end return
} //end fucntion

//  wrapper
export default withTranslation()(CategoryCreate)