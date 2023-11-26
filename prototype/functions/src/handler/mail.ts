import {firestore} from "../config/firebase";
import {Mail} from "../model/mail";

const mailColl = firestore.collection("mail");

export const sendMailHandler = async (mail: Mail)=> {
  await mailColl.add(mail);
};
