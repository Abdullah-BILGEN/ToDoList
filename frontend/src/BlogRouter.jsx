// rcc
import React, { Component } from 'react'

// ROUTER
import { Navigate, Route, Routes } from 'react-router-dom';

// I18N
import { withTranslation } from 'react-i18next';

// HEADER,FOOTER,MAIN
import Footer from './component/Footer';
import Header from './component/Header';
import Main from './component/Main';

// TASK
import TaskList from './component/task/TaskList';
import TaskCreate from './component/task/TaskCreate';
import TaskView from './component/task/TaskView';
import TaskUpdate from './component/task/TaskUpdate';


// CLASS COMPONENT
class BlogRouter extends Component {

    // Component görünen ismi
    static displayName = "Blog_Router";

    // Constructor
    constructor(props) {
        super(props);

        // STATE
        this.state = {}

        // BIND
    } //end constructor

    // CDM

    // FUNCTION

    //RENDER
    render() {

        //RETURN
        return (
            <React.Fragment>
                <Header logo="fa-solid fa-warehouse" />

                <div className="container">
                    <Routes>
                        <Route path='/' element={<Main />} />

                        {/* Task */}
                        <Route path='/task/list' element={<TaskList list="task"/>} />
                        <Route path='/task/create' element={<TaskCreate/>} />
                        <Route path='/task/view/:id' element={<TaskView/>} />
                        <Route path='/task/update/:id' element={<TaskUpdate/>} />
                        {/* bad request */}
                        <Route path="*" element={<Navigate to="/" />} />
                    </Routes>
                </div>

                <Footer copy="&copy; 2021 - 2023" />
            </React.Fragment>
        ) //end return
    } //end render
} //end class

// Higher Order Component
export default withTranslation()(BlogRouter);
