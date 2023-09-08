// AXIOS
import axios from "axios";

// Persist URL
const CATEGORY_URL = "/todo/api/v1";

// CLASS API
class CategoryApi {
    // CREATE
    // http://localhost:4444/category/api/v1/create
    // @PostMapping("/create")</CategoryDto>
    categoryApiCreate(toDoDto) {
        return axios.post(`${CATEGORY_URL}/create`, toDoDto);
    }

    // LIST
    // @GetMapping(value="/list")
    // http://localhost:4444/category/api/v1/list
    categoryApiList() {
        return axios.get(`${CATEGORY_URL}/list`);
    }

    // FIND
    // http://localhost:4444/category/api/v1/find/1
    //@GetMapping(value="/find/{id}")
    categoryApiFindById(id) {
        return axios.get(`${CATEGORY_URL}/find/${id}`);
    }

    // UPDATE
    // http://localhost:4444/category/api/v1/update/1
    //@PutMapping(value="/update/{id}")
    categoryApiUpdate(id, toDoDto) {
        return axios.put(`${CATEGORY_URL}/update/${id}`, toDoDto);
    }

    // UPDATE STATUS
    // http://localhost:4444/category/api/v1/updateStatus/1
    //@PutMapping(value="/updateStatus/{id}")
    categoryApiUpdateStatus(id, toDoDto) {
        return axios.put(`${CATEGORY_URL}/updateStatus/${id}`, toDoDto);
    }

    // DELETE BY ID
    // http://localhost:4444/category/api/v1/delete/1
    //@DeleteMapping(value="/delete/{id}")
    categoryApiDeleteById(id) {
        return axios.delete(`${CATEGORY_URL}/delete/${id}`);
    }

    // DELETE ALL
    // http://localhost:4444/todo/api/v1/delete/all
    //@DeleteMapping(value="/delete/all")
    categoryApiDeleteAll() {
        return axios.delete(`${CATEGORY_URL}/delete/all`);
    }

    // DELETE COMPLETED
    // http://localhost:4444/todo/api/v1/delete/all
    //@DeleteMapping(value="/delete/completed")
    categoryApiDeleteCompleted() {
        return axios.delete(`${CATEGORY_URL}/delete/completed`);
    }
} //end class

export default new CategoryApi();
