/* eslint-disable */

import {IsEmail, IsString, Matches, Min} from "class-validator";

export class User {
    @IsEmail()
    email: string;

    @Matches(/^(?!(([A-Za-z]+)|([~!@#$%^&*()_+=]+)|([0-9]+))$)[A-Za-z\\d~!@#$%^&*()_+=]{8,16}$/)
    public password: string;

    @IsString()
    @Min(1)
    public name: string;

    @Matches(/^010(\d{4})(\d{4})$/)
    public tel: string;

    @Matches(/^([0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))$/)
    public readonly birth: string;

    public constructor(
      email: string,
      password: string,
      name: string,
      tel: string,
      birth: string
    ) {
      this.email = email;
      this.password = password;
      this.name = name;
      this.tel = tel;
      this.birth = birth;
    }
}

export type LoginDTO = Pick<User, "email" | "password">;
export type UpdateDTO = Partial<LoginDTO>;
