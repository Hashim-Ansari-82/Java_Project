import { useEffect, useState } from "react";
import {
  FaPlus,
  FaSearch,
  FaEdit,
  FaTrash,
  FaFilePrescription
} from "react-icons/fa";

import API from "../api/axiosConfig";


function Prescriptions() {


  const [prescriptions, setPrescriptions] = useState([]);
  const [appointments, setAppointments] = useState([]);

  const [search, setSearch] = useState("");

  const [showModal, setShowModal] = useState(false);

  const [isEdit, setIsEdit] = useState(false);



  const [formData, setFormData] = useState({

    id: null,
    medicine: "",
    instruction: "",
    appointmentId: ""

  });



  useEffect(() => {

    fetchPrescriptions();
    fetchAppointments();

  }, []);





  // GET ALL PRESCRIPTIONS

  const fetchPrescriptions = async () => {

    try {

      const response = await API.get("/prescriptions");

      setPrescriptions(response.data);

    }
    catch (error) {

      console.log(error);

    }

  };





  // GET APPOINTMENTS

  const fetchAppointments = async () => {

    try {

      const response = await API.get("/appointments");

      setAppointments(response.data);

    }
    catch(error){

      console.log(error);

    }

  };







  const handleChange = (e) => {

    setFormData({

      ...formData,

      [e.target.name]: e.target.value

    });

  };







  // SAVE / UPDATE

  const handleSubmit = async(e)=>{


    e.preventDefault();


    const payload = {

      medicine: formData.medicine,

      instruction: formData.instruction,

      appointmentId: Number(formData.appointmentId)

    };



    try {


      if(isEdit){


        await API.put(

          `/prescriptions/${formData.id}`,

          payload

        );


      }
      else{


        await API.post(

          "/prescriptions",

          payload

        );


      }



      closeModal();

      fetchPrescriptions();



    }
    catch(error){


      console.log(error.response?.data);


    }



  };








  // EDIT

  const handleEdit=(item)=>{


    setFormData({

      id:item.id,

      medicine:item.medicine,

      instruction:item.instruction,

      appointmentId:""

    });


    setIsEdit(true);

    setShowModal(true);


  };








  // DELETE

  const handleDelete=async(id)=>{


    if(!window.confirm("Delete Prescription?"))
      return;


    try{


      await API.delete(

        `/prescriptions/${id}`

      );


      fetchPrescriptions();


    }
    catch(error){

      console.log(error);

    }


  };








  const closeModal=()=>{


    setShowModal(false);

    setIsEdit(false);


    setFormData({

      id:null,

      medicine:"",

      instruction:"",

      appointmentId:""

    });


  };







  const filteredData = prescriptions.filter((item)=>

    item.medicine
    .toLowerCase()
    .includes(search.toLowerCase())

  );






return (

<div className="p-6 bg-gray-100 min-h-screen">



{/* HEADER */}

<div className="flex justify-between items-center mb-6">


<div>

<h1 className="text-3xl font-bold text-slate-800 flex items-center gap-3">

<FaFilePrescription className="text-blue-600"/>

Prescription

</h1>


<p className="text-gray-500">

Manage patient prescriptions

</p>


</div>




<button

onClick={()=>setShowModal(true)}

className="
bg-blue-600
hover:bg-blue-700
text-white
px-5
py-2
rounded-lg
flex
items-center
gap-2
"


>

<FaPlus/>

Add Prescription

</button>


</div>








{/* SEARCH */}

<div className="
bg-white
p-4
rounded-xl
shadow
mb-5
flex
items-center
gap-3
">


<FaSearch className="text-gray-400"/>


<input

type="text"

placeholder="Search medicine..."

value={search}

onChange={(e)=>setSearch(e.target.value)}

className="
w-full
outline-none
"

/>


</div>









{/* TABLE */}

<div className="
bg-white
rounded-xl
shadow
overflow-hidden
">


<table className="w-full">


<thead className="bg-blue-600 text-white">


<tr>


<th className="p-4">
ID
</th>


<th>
Medicine
</th>


<th>
Instruction
</th>


<th>
Action
</th>


</tr>


</thead>



<tbody>


{

filteredData.map((item)=>(


<tr

key={item.id}

className="
border-b
hover:bg-blue-50
"


>


<td className="text-center p-3">

{item.id}

</td>



<td className="text-center font-semibold">

{item.medicine}

</td>



<td className="text-center">

{item.instruction}

</td>




<td className="text-center">


<button

onClick={()=>handleEdit(item)}

className="
text-blue-600
mr-4
"

>

<FaEdit/>

</button>




<button

onClick={()=>handleDelete(item.id)}

className="
text-red-600
"

>

<FaTrash/>

</button>



</td>



</tr>


))


}



</tbody>


</table>



</div>









{/* MODAL */}


{

showModal &&


<div className="
fixed
inset-0
bg-black/40
flex
justify-center
items-center
">


<div className="
bg-white
w-[420px]
rounded-xl
p-6
shadow-xl
">


<h2 className="text-xl font-bold mb-5">


{
isEdit
?
"Update Prescription"
:
"Add Prescription"

}


</h2>





<form onSubmit={handleSubmit} className="space-y-4">



<input

name="medicine"

value={formData.medicine}

onChange={handleChange}

placeholder="Medicine Name"

className="
w-full
border
p-3
rounded-lg
"

required

/>





<textarea

name="instruction"

value={formData.instruction}

onChange={handleChange}

placeholder="Instruction"

rows="3"

className="
w-full
border
p-3
rounded-lg
"

required

/>






<select

name="appointmentId"

value={formData.appointmentId}

onChange={handleChange}

className="
w-full
border
p-3
rounded-lg
"

required

>


<option value="">

Select Appointment

</option>



{

appointments.map((a)=>(


<option

key={a.id}

value={a.id}

>


Appointment #{a.id} - {a.date}


</option>


))


}



</select>







<div className="flex justify-end gap-3">


<button

type="button"

onClick={closeModal}

className="
bg-gray-300
px-4
py-2
rounded-lg
"

>

Cancel

</button>




<button

className="
bg-blue-600
text-white
px-5
py-2
rounded-lg
"

>

Save

</button>



</div>



</form>



</div>



</div>


}



</div>


);


}


export default Prescriptions;