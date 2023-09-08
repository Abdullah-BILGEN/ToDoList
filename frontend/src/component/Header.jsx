// rcc
import React, { Component } from 'react'

// I18N
import { withTranslation } from 'react-i18next';

// ROUTER
import { Link } from 'react-router-dom';

// Language
import OtherLanguageServices from "../internationalization/OtherLanguageServices";

// Flag (Dil)
import tr from "../img/flag/tr.png"
import en from "../img/flag/en.png"


// CLASS COMPONENT
class Header extends Component {

    // Component görünen ismi
    static displayName = "Blog_Header";

    // Constructor
    constructor(props) {
        super(props);

        // STATE
        this.state = {}

        // BIND
    } //end constructor

    // CDM

    // FUNCTION

    //Bayraklar
    internationalizationLanguage = language => {
        const { i18n } = this.props;
        i18n.changeLanguage(language);

        //Hem java tarafından hemde frontend tarafından değişiklik yaptık.
        OtherLanguageServices.headerLanguageServices(language);
    }

    //RENDER
    render() {

        //RETURN
        return (
            <React.Fragment>
                <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                    <div className="container">
                        <Link className="navbar-brand" to="/"> <i className={this.props.logo}></i></Link>
                        <button
                            className="navbar-toggler d-lg-none"
                            type="button"
                            data-bs-toggle="collapse"
                            data-bs-target="#collapsibleNavId"
                            aria-controls="collapsibleNavId"
                            aria-expanded="false"
                            aria-label="Toggle navigation"
                        >
                            <span className="navbar-toggler-icon" />
                        </button>
                        <div className="collapse navbar-collapse" id="collapsibleNavId">
                            <ul className="navbar-nav me-auto mt-2 mt-lg-0">
                                <li className="nav-item">
                                    <Link className="nav-link active" to="/" aria-current="page">
                                        Home <span className="visually-hidden">(current)</span>
                                    </Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to="/category/list">
                                        To Do List
                                    </Link>
                                </li>
                                <li className="nav-item dropdown">
                                    <a
                                        className="nav-link dropdown-toggle"
                                        href="#"
                                        id="dropdownId"
                                        data-bs-toggle="dropdown"
                                        aria-haspopup="true"
                                        aria-expanded="false"
                                    >
                                        Dil
                                    </a>
                                    <div className="dropdown-menu" aria-labelledby="dropdownId">
                                        <a className="dropdown-item" href="#" onClick={() => this.internationalizationLanguage('tr')}>
                                            TR
                                        </a>
                                        <a className="dropdown-item" href="#" onClick={() => this.internationalizationLanguage('en')}>
                                            EN
                                        </a>
                                    </div>
                                </li>
                            </ul>

                            <ul className="navbar-nav ms-auto mt-2 mt-lg-0">
                                <li className="nav-item">
                                    <a  href="#" onClick={() => this.internationalizationLanguage('tr')}>
                                        <img src={tr} alt="" className="rounded-circle" style={{width:"50px",marginRight:"10px"}}  />
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a  href="#" onClick={() => this.internationalizationLanguage('en')}>
                                        <img src={en} alt=""  style={{width:"40px",marginRight:"10px"}}  />
                                    </a>
                                </li>
                            </ul>

                            <form className="d-flex my-2 my-lg-0">
                                <input
                                    className="form-control me-sm-2"
                                    type="text"
                                    placeholder="Search"
                                />
                                <button className="btn btn-outline-success my-2 my-sm-0" type="submit">
                                    Search
                                </button>
                            </form>
                        </div>
                    </div>
                </nav>

            </React.Fragment>
        ) //end return
    } //end render
} //end class

// Higher Order Component
export default withTranslation()(Header);