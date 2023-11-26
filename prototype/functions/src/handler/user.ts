import {firestore} from "../config/firebase";
import {UpdateDTO, User} from "../model/user";

const userColl = firestore.collection("users");

const findByEmail = async (email: string) => {
  const snapshot = await userColl
    .where("email", "==", email)
    .get();

  return snapshot.empty ? null : snapshot.docs[0];
};

export const createUserHandler = async (user: User) => {
  if (await findByEmail(user.email)) {
    throw new Error("Email already exists");
  }

  await userColl.add(user);
};

export const getUserHandler = async (email: string) => {
  const doc = await findByEmail(email);
  if (doc === null) throw new Error("Unexpected email");
  return doc.data() as User;
};

export const updateUserHandler = async (email: string, field: UpdateDTO) => {
  const id = (await findByEmail(email))!.id;
  await userColl.doc(id).update(field);
};

export const deleteUserHandler = async (email: string) => {
  const id = (await findByEmail(email))!.id;
  await userColl.doc(id).delete();
};
