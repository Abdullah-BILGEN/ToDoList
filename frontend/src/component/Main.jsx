import React, { Component } from 'react'
import { Link } from 'react-router-dom'

// Resim
import moon from "../img/moon.jpg";

export default class Main extends Component {
  render() {
    return (
      <React.Fragment>
        <h1 className="display-3">Ana Sayfa</h1>
        <img src={moon} alt="" style={{width:"100%"}} />
         <Link className="btn btn-primary mt-5 mb-5" to="task/list">Task - List</Link>
      </React.Fragment>
    )
  }
}