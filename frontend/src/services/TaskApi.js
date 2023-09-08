// AXIOS
import axios from "axios";

// Persist URL 
const TASK_URL = "/task/api/v1"

// CLASS API
class TaskApi {

    // CREATE
    // http://localhost:4444/category/api/v1/create
    // @PostMapping("/create")</CategoryDto>
    taskApiCreate(taskDto) {
        return axios.post(`${TASK_URL}/create`, taskDto)
    };

    // LIST
    // @GetMapping(value="/list")
    // http://localhost:4444/category/api/v1/list
    taskApiList() {
        return axios.get(`${TASK_URL}/list`)
    }

    // FIND
    // http://localhost:4444/category/api/v1/find/1
    //@GetMapping(value="/find/{id}")
    taskApiFindById(id) {
        return axios.get(`${TASK_URL}/find/${id}`)
    }

    // UPDATE
    // http://localhost:4444/category/api/v1/update/1
    //@PutMapping(value="/update/{id}")
    taskApiUpdate(id, taskDto) {
        return axios.put(`${TASK_URL}/update/${id}`, taskDto)
    }

  

    // DELETE BY ID
    // http://localhost:4444/category/api/v1/delete/1
    //@DeleteMapping(value="/delete/{id}")
    taskApiDeleteById(id) {
        return axios.delete(`${TASK_URL}/delete/${id}`)
    }





    

} //end class
const instance = new TaskApi();

export default instance;

