<template xmlns="http://www.w3.org/1999/html">
  <div>

    <information :action-validation-msg="[
      'Las validacion comienza cuando el campo pierde el foco.',
      'El botón de limpiar reinicia el formulario.',
      'El formulario se limpia después de ser enviado'
    ]" :validation-msg="[
      'El valor del campo es requerido.',
      'El campo solo acepta archivos de tipo imagen.',
      'El campo solo acepta archivos con un tamaño menor a 2MB.',
      'El campo solo acepta archivos con un tamaño mayor a 100KB.',
      'El campo solo acepta archivos con un ancho mayor a 100px y menor a 1000px y un alto mayor a 100px y menor a 1000px.',
      'El campo solo acepta archivos con un nombre de archivo que no contenga espacios en blanco.',
      'El campo solo acepta archivos con un nombre de archivo que no contenga caracteres especiales.'
    ]"/>

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
        <sent-form :simulation-show="simulationShow" :simulation-send="simulationSend"/>

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
import {onSimulateSend, onSimulateShow} from "@/assets/js/functions";


const base64Encode = data =>
    new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(data);
      reader.onload = () => resolve(reader.result);
      reader.onerror = error => reject(error);
    });

export default Vue.extend({
  name: "InputEmail",
  components: {
    Information: () => import('@/components/Information.vue'),
    SentForm: () => import('@/components/SentForm.vue'),
  },
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
      if (!isFormCorrect) return

      this.simulationShow = true;
      this.simulationSend = false;
      this.simulationSend = await onSimulateSend()
      this.simulationShow = await onSimulateShow()

      this.onReset()
    },
    onReset() {
      this.form.file = null;
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
            value => {
              if (!value) return true
              return value && value.type.includes("image")
            }
        ),
        size: helpers.withMessage(
            "El campo solo acepta archivos con un tamaño menor a 2MB",
            value =>{
              if (!value) return true
              return value && value.size < 2000000
            }
        ),
        sizeMin: helpers.withMessage(
            "El campo solo acepta archivos con un tamaño mayor a 100bytes",
            value =>{
              if (!value) return true
              return value && value.size > 100
            }
        ),
        width: helpers.withMessage(
            "El campo solo acepta archivos con un ancho mayor a 100px y menor a 2000px",
            (value, {imageDimensions}) => {
              if (!value) return true
              return value && imageDimensions.width > 100 && imageDimensions.width < 20000
            }
        ),
        height: helpers.withMessage(
            "El campo solo acepta archivos con un alto mayor a 100px y menor a 2000px",
            (value, {imageDimensions}) => {
              if (!value) return true
              return value && imageDimensions.height > 100 && imageDimensions.height < 20000
            }
        ),
        name: helpers.withMessage(
            "El campo solo acepta archivos con un nombre de archivo que no contenga espacios en blanco",
            value => {
              if (!value) return true
              return value && !value.name.includes(" ")
            }
        ),
        nameSpecial: helpers.withMessage(
            "El campo solo acepta archivos con un nombre de archivo que no contenga caracteres especiales",
            value => {
              if (!value) return true
              return value && !value.name.match(/[!@#$%^&*(),?":{}|<>]/)
            }
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
