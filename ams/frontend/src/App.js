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
import ChiefDisciplines from "./components/ChiefTeacherComponents/ChiefDisciplines/ChiefDisciplines";
import ChiefTeacherGrades from "./components/ChiefTeacherComponents/ChiefTeacherGrades/ChiefTeacherGrades";
import ChiefOptionalsAssign from "./components/ChiefTeacherComponents/ChiefOptionalsAssign/ChiefOptionalsAssign";
import ChiefOptionalsAccept from "./components/ChiefTeacherComponents/ChiefOptionals/ChiefOptionalsAccept";
import TeacherOptional from "./components/TeacherComponents/TeacherOptionals/TeacherOptional";
import TeacherGrades from "./components/TeacherComponents/TeacherGrades/TeacherGrades";
function App() {
  return (
    <div>
      {" "}
      <Routes>
        <Route exact path="/login" element={<LoginForm />} />
        <Route exact path="/" element={<Menu userName={JSON.parse(localStorage.getItem('userToken')).username} role={JSON.parse(localStorage.getItem('userToken')).roles[0]} />} />
        <Route exact path="/enroll" element={<Enroll />} />
        <Route exact path="/grades" element={<Grades />} />
        <Route exact path="/optionals" element={<Optionals />} />
        <Route exact path="/contract" element={<Contract />} />
        <Route exact path="/chief_disciplines" element={<ChiefDisciplines />} />
        <Route exact path="/chief_teachers" element={<ChiefTeacherGrades />} />
        <Route exact path="/chief_optionals_accept" element={<ChiefOptionalsAccept />} />
        <Route exact path="/chief_optionals_assign" element={<ChiefOptionalsAssign />} /> 
        <Route exact path="/teacher_propose_optionals" element={<TeacherOptional/>} />
        <Route exact path="/teacher_grades" element={<TeacherGrades/>} />

        <Route exact path="ProfilePage" element={<ProfilePage />} />
      </Routes>
    </div>
  );
}

export default App;
