<template xmlns="http://www.w3.org/1999/html">
  <div>

    <b-card no-body>
      <b-card-header v-b-toggle.collapseValidations>
        <span class="border-bottom text-primary">Información de validación de un campo para subir imagenes</span>
      </b-card-header>
      <b-collapse id="collapseValidations">
        <b-card-body>
          <b-card-text>
            <ul class="px-3">
              <li>El valor del campo es requerido.</li>
              <li>El campo solo acepta archivos de tipo imagen.</li>
              <li>El campo solo acepta archivos con un tamaño menor a 2MB.</li>
              <li>El campo solo acepta archivos con un tamaño mayor a 100KB.</li>
              <li>
                El campo solo acepta archivos con un ancho mayor a 100px y menor a 1000px y un alto mayor a 100px y
                menor a 1000px.
              </li>
              <li>El campo solo acepta archivos con un nombre de archivo que no contenga espacios en blanco.</li>
              <li>El campo solo acepta archivos con un nombre de archivo que no contenga caracteres especiales.</li>
            </ul>
          </b-card-text>
        </b-card-body>
      </b-collapse>


      <b-card-header v-b-toggle.collapseValidationFunction>
        <span class="border-bottom text-primary">Acerca de las acciones al campo</span>
      </b-card-header>
      <b-collapse id="collapseValidationFunction">
        <b-card-body>
          <b-card-text>
            <ul class="px-3">
              <li>Las validacion comienza cuando el campo pierde el foco.</li>
              <li>El botón de enviar valida el formulario.</li>
              <li>El botón de limpiar reinicia el formulario.</li>
            </ul>
          </b-card-text>
        </b-card-body>
      </b-collapse>
    </b-card>

    <b-card class="mt-3">
      <b-form @submit.prevent="onSubmit">
        <b-form-group label="Archivo Imagenes">
          <!-- Styled -->
          <b-form-file
              v-model="v$.form.file.$model"
              placeholder="Selecciona o arrastra un archivo aquí..."
              drop-placeholder="Suelta el archivo aquí..."
              browse-text="Buscar"
              accept="image/*"
              ref="fileInput"
              :file-name-formatter="(files) =>`${files[0].name} - ${files[0].size} bytes`"
              :state="!v$.form.file.$dirty ? null : !v$.form.file.$error"
          ></b-form-file>

          <b-img :src="imageSrc" class="mb-3" fluid block rounded style="max-height: 400px"></b-img>

          <div class="mt-3" v-if="form.file">Archivo Seleccionado: {{ form.file ? form.file.name : '' }}</div>

          <b-form-invalid-feedback v-for="error in v$.form.file.$errors" :key="error.$uid">
            {{ error.$message }}
          </b-form-invalid-feedback>
        </b-form-group>

        <!--    message to simulate the sending of the form    -->
        <div v-if="simulationShow">
          <div v-if="simulationSend" class="text-success">
            <strong>¡Enviado!</strong> El formulario ha sido enviado.
          </div>
          <div v-else class="text-secondary">
            <strong>Cargando...</strong> Espere un momento.
          </div>
        </div>

        {{


         this.form.imageDimensions
        }}


        <div class="text-right">
          <b-button type="reset" variant="danger" class="mx-3" @click="onReset">Limpiar</b-button>
          <b-button type="submit" variant="primary" @click="() => v$.form.$touch()">Enviar</b-button>
        </div>
      </b-form>
    </b-card>


  </div>
</template>

<script>
import Vue from "vue";
import {useVuelidate} from "@vuelidate/core";
import {required, alphaNum, helpers, maxLength, minLength, email} from "@vuelidate/validators";


const base64Encode = data =>
    new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(data);
      reader.onload = () => resolve(reader.result);
      reader.onerror = error => reject(error);
    });

export default Vue.extend({
  name: "InputEmail",
  setup() {
    return {v$: useVuelidate()};
  },
  watch: {
    "form.file": function (newValue, oldValue) {
      if (newValue !== oldValue) {
        if (newValue) {
          const file = newValue;
          //check width and height
          const img = new Image();
          img.src = URL.createObjectURL(file);
          img.onload = () => {
            this.form.imageDimensions.width = img.width;
            this.form.imageDimensions.height = img.height;
          };


          base64Encode(newValue).then((data) => {
            this.imageSrc = data;
          }).catch((error) => {
            this.imageSrc = null;
          });
        } else this.imageSrc = null;
      }
    },
  },
  data() {
    return {
      form: {
        file: null,
        imageDimensions: {
          width: 0,
          height: 0,
        },
      },
      imageSrc: null,
      simulationShow: false,
      simulationSend: false,
    };
  },
  methods: {
    onInput(e) {
      console.log('click')
      this.v$.form.file.$touch();
    },
    async onSubmit() {

      const isFormCorrect = await this.v$.$validate()
      if (!isFormCorrect) {
        return
      }


      this.simulationShow = true;
      this.simulationSend = false;
      setTimeout(() => {
        this.simulationSend = true;
      }, 1000);
      setTimeout(() => {
        this.simulationShow = false;
      }, 2000);
    },
    onReset() {
      this.v$.$reset();
    },
  },
  validations: {
    form: {
      file: {
        required: helpers.withMessage(
            "El campo es requerido",
            required
        ),
        image: helpers.withMessage(
            "El campo solo acepta archivos de tipo imagen",
            value => value && value.type.includes("image")
        ),
        size: helpers.withMessage(
            "El campo solo acepta archivos con un tamaño menor a 2MB",
            value => value && value.size < 2000000
        ),
        sizeMin: helpers.withMessage(
            "El campo solo acepta archivos con un tamaño mayor a 100bytes",
            value => value && value.size > 100
        ),
        width: helpers.withMessage(
            "El campo solo acepta archivos con un ancho mayor a 100px y menor a 2000px",
            (value, {imageDimensions}) => value && imageDimensions.width > 100 && imageDimensions.width < 20000
        ),
        height: helpers.withMessage(
            "El campo solo acepta archivos con un alto mayor a 100px y menor a 2000px",
            (value, {imageDimensions}) => value && imageDimensions.height > 100 && imageDimensions.height < 20000
        ),
        name: helpers.withMessage(
            "El campo solo acepta archivos con un nombre de archivo que no contenga espacios en blanco",
            value => value && !value.name.includes(" ")
        ),
        nameSpecial: helpers.withMessage(
            "El campo solo acepta archivos con un nombre de archivo que no contenga caracteres especiales",
            value => value && !value.name.match(/[!@#$%^&*(),?":{}|<>]/)
        ),
      },
      imageDimensions:{
        width:{},
        height:{}
      }
    },
  },
});
</script>

<style scoped></style>
