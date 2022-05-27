import "./App.css";
import Menu from "./components/Menu/Menu";
import Enroll from "./components/StudentComponents/Enroll/Enroll";
import Grades from "./components/StudentComponents/Grades/Grades";
import Optionals from "./components/StudentComponents/Optionals/Optionals";
import Contract from "./components/StudentComponents/Contract/Contract";
// import Sylabus from "./components/Syllabus/Syllabus";
import ProfilePage from "./components/ProfilePage/ProfilePage";
import LoginForm from "./components/LoginForm/LoginForm";
import { Link } from "react-router-dom";
import { Routes, Route } from "react-router-dom";
function App() {
  return (
    <div>
      {" "}
      <Routes>
        <Route exact path="/login" element={<LoginForm />} />
        <Route exact path="/" element={<Menu />} />
        <Route exact path="/enroll" element={<Enroll />} />
        <Route exact path="/grades" element={<Grades />} />
        <Route exact path="/optionals" element={<Optionals />} />
        <Route exact path="/contract" element={<Contract />} />
        {/*<Route exact path="/syllabus" element={<Sylabus id={2} />} />*/}
        <Route exact path="ProfilePage" element={<ProfilePage />} />
      </Routes>
    </div>
  );
}

export default App;
